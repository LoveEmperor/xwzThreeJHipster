import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';
import { InvoiceInfoService } from './invoice-info.service';

@Component({
    selector: 'jhi-invoice-info-delete-dialog',
    templateUrl: './invoice-info-delete-dialog.component.html'
})
export class InvoiceInfoDeleteDialogComponent {
    invoiceInfo: IInvoiceInfo;

    constructor(
        private invoiceInfoService: InvoiceInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.invoiceInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'invoiceInfoListModification',
                content: 'Deleted an invoiceInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-invoice-info-delete-popup',
    template: ''
})
export class InvoiceInfoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ invoiceInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(InvoiceInfoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.invoiceInfo = invoiceInfo;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
