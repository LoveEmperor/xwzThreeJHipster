import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IEInvoiceInfo } from 'app/shared/model/e-invoice-info.model';
import { Principal } from 'app/core';
import { EInvoiceInfoService } from './e-invoice-info.service';

@Component({
    selector: 'jhi-e-invoice-info',
    templateUrl: './e-invoice-info.component.html'
})
export class EInvoiceInfoComponent implements OnInit, OnDestroy {
    eInvoiceInfos: IEInvoiceInfo[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private eInvoiceInfoService: EInvoiceInfoService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.eInvoiceInfoService.query().subscribe(
            (res: HttpResponse<IEInvoiceInfo[]>) => {
                this.eInvoiceInfos = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInEInvoiceInfos();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IEInvoiceInfo) {
        return item.id;
    }

    registerChangeInEInvoiceInfos() {
        this.eventSubscriber = this.eventManager.subscribe('eInvoiceInfoListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
