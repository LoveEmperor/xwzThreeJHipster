import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEInvoiceInfo } from 'app/shared/model/e-invoice-info.model';

@Component({
    selector: 'jhi-e-invoice-info-detail',
    templateUrl: './e-invoice-info-detail.component.html'
})
export class EInvoiceInfoDetailComponent implements OnInit {
    eInvoiceInfo: IEInvoiceInfo;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eInvoiceInfo }) => {
            this.eInvoiceInfo = eInvoiceInfo;
        });
    }

    previousState() {
        window.history.back();
    }
}
