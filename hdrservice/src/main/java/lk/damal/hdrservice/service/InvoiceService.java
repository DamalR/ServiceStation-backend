package lk.damal.hdrservice.service;

import lk.damal.hdrservice.dto.InvoiceDTO;
import lk.damal.hdrservice.dto.ResponseDTO;

public interface InvoiceService {
    public ResponseDTO printInvoice(InvoiceDTO invoiceDTO);
}
