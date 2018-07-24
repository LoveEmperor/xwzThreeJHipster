import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';

@Component({
    selector: 'jhi-invoice-info-detail',
    templateUrl: './invoice-info-detail.component.html'
})
export class InvoiceInfoDetailComponent implements OnInit {
    invoiceInfo: IInvoiceInfo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ invoiceInfo }) => {
            this.invoiceInfo = invoiceInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
