package lk.damal.hdrservice.service.impl;

import lk.damal.hdrservice.dto.AppointmentDTO;
import lk.damal.hdrservice.dto.InvoiceDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.model.Appointment;
import lk.damal.hdrservice.model.Invoice;
import lk.damal.hdrservice.model.ServiceData;
import lk.damal.hdrservice.repository.AppointmentRepository;
import lk.damal.hdrservice.repository.InvoiceRepository;
import lk.damal.hdrservice.repository.ServiceRepository;
import lk.damal.hdrservice.service.AppointmentService;
import lk.damal.hdrservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private AppointmentService appointmentService;

    @Override
    @Transactional
    public ResponseDTO printInvoice(InvoiceDTO invoiceDTO) {

        long serviceId = invoiceDTO.getServiceId();
        String invoiceNumber = invoiceDTO.getInvoiceNumber();
        String customerName = invoiceDTO.getCustomerName();
        String vehicleNumber = invoiceDTO.getVehicleNumber();
        String job = invoiceDTO.getJob();
        String usedOilBrand = invoiceDTO.getUsedOilBrand();
        String usedOilViscosity = invoiceDTO.getUsedOilViscosity();
        String serviceDate = invoiceDTO.getServiceDate();
        String paymentMethod = invoiceDTO.getPaymentMethod();
        int vehicleMileage = invoiceDTO.getVehicleMileage();
        double usedOilLiters = invoiceDTO.getUsedOilLiters();
        double usedOilPrice = invoiceDTO.getUsedOilPrice();
        double grossAmount = invoiceDTO.getGrossAmount();
        double discountAmount = invoiceDTO.getDiscountAmount();
        double netAmount = invoiceDTO.getNetAmount();

        Optional<ServiceData> serviceById = serviceRepository.findById(serviceId);

        if (serviceById.isPresent()) {

            ServiceData serviceData = serviceById.get();
            String status = serviceData.getStatus();

            if (status.equalsIgnoreCase("Done") || status.equalsIgnoreCase("Returned")) {
                if (serviceId == 0) {
                    return new ResponseDTO(
                            false,
                            "Service id cannot be null!"
                    );
                } else if (invoiceNumber.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Invoice number cannot be null!"
                    );
                } else if (customerName.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Customer name cannot be null!"
                    );
                }
                else if (vehicleNumber.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Customer name cannot be null!"
                    );
                } else if (job.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Job detail cannot be null!"
                    );
                } else if (serviceDate.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Service Date cannot be null!"
                    );
                } else if (paymentMethod.equalsIgnoreCase("")) {
                    return new ResponseDTO(
                            false,
                            "Payment method cannot be null!"
                    );
                } else if (vehicleMileage == 0) {
                    return new ResponseDTO(
                            false,
                            "Vehicle mileage cannot be null!"
                    );
                } else if (grossAmount == 0) {
                    return new ResponseDTO(
                            false,
                            "Gross amount cannot be null!"
                    );
                } else if (netAmount == 0) {
                    return new ResponseDTO(
                            false,
                            "Net amount cannot be null!"
                    );
                } else {
                    try {
//                        update appointment status

                        AppointmentDTO appointmentDTO = new AppointmentDTO();

                        appointmentDTO.setDate(serviceById.get().getAppointment().getDate());
                        appointmentDTO.setTime(serviceById.get().getAppointment().getTime());
                        appointmentDTO.setStatus("Done");
                        appointmentDTO.setCustomerId(serviceById.get().getAppointment().getCustomer().getCustomerId());
                        appointmentDTO.setVehicleId(serviceById.get().getAppointment().getVehicle().getVehicleId());

                        appointmentService.updateAppointmentStatus(appointmentDTO, serviceById.get().getAppointment().getAppointmentId());

//                        invoice print transaction
                        Invoice invoice = new Invoice();

                        invoice.setInvoiceNumber(invoiceNumber);
                        invoice.setJob(job);
                        invoice.setCustomerName(customerName);
                        invoice.setServiceDate(serviceDate);
                        invoice.setDiscountAmount(discountAmount);
                        invoice.setGrossAmount(grossAmount);
                        invoice.setNetAmount(netAmount);
                        invoice.setPaymentMethod(paymentMethod);
                        invoice.setUsedOilBrand(usedOilBrand);
                        invoice.setUsedOilLiters(usedOilLiters);
                        invoice.setUsedOilPrice(usedOilPrice);
                        invoice.setUsedOilViscosity(usedOilViscosity);
                        invoice.setVehicleMileage(vehicleMileage);
                        invoice.setVehicleNumber(vehicleNumber);
                        invoice.setServiceData(serviceById.get());

                        invoiceRepository.save(invoice);

                        InvoiceDTO savedInvoice = new InvoiceDTO();

                        savedInvoice.setInvoiceId(invoice.getInvoiceId());
                        savedInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
                        savedInvoice.setJob(invoice.getJob());
                        savedInvoice.setCustomerName(invoice.getCustomerName());
                        savedInvoice.setServiceDate(invoice.getServiceDate());
                        savedInvoice.setDiscountAmount(invoice.getDiscountAmount());
                        savedInvoice.setGrossAmount(invoice.getGrossAmount());
                        savedInvoice.setNetAmount(invoice.getNetAmount());
                        savedInvoice.setPaymentMethod(invoice.getPaymentMethod());
                        savedInvoice.setUsedOilBrand(invoice.getUsedOilBrand());
                        savedInvoice.setUsedOilLiters(invoice.getUsedOilLiters());
                        savedInvoice.setUsedOilPrice(invoice.getUsedOilPrice());
                        savedInvoice.setUsedOilViscosity(invoice.getUsedOilViscosity());
                        savedInvoice.setVehicleMileage(invoice.getVehicleMileage());
                        savedInvoice.setVehicleNumber(invoice.getVehicleNumber());
                        savedInvoice.setServiceId(invoice.getServiceData().getServiceId());

                        return new ResponseDTO(
                                true,
                                "Invoice processed successfully!",
                                savedInvoice
                        );
                    } catch (Exception exception) {
                        return new ResponseDTO(
                                false,
                                "Sorry, Cannot provide an invoice!",
                                exception
                        );
                    }
                }
            } else {
                return new ResponseDTO(
                        false,
                        "Sorry, Cannot provide invoice without service done or returned!"
                );
            }
        } else {
            return new ResponseDTO(
                    false,
                    "Cannot provide invoice without service data!"
            );
        }
    }
}
