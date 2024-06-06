package lk.damal.hdrservice.controller;

import lk.damal.hdrservice.dto.InvoiceDTO;
import lk.damal.hdrservice.dto.ResponseDTO;
import lk.damal.hdrservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

//    @PostMapping("/print")
//    public ResponseDTO printInvoice(@RequestBody InvoiceDTO invoiceDTO) {
//        return invoiceService
//    }
}
