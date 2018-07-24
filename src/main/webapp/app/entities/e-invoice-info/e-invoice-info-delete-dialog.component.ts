import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEInvoiceInfo } from 'app/shared/model/e-invoice-info.model';
import { EInvoiceInfoService } from './e-invoice-info.service';

@Component({
    selector: 'jhi-e-invoice-info-delete-dialog',
    templateUrl: './e-invoice-info-delete-dialog.component.html'
})
export class EInvoiceInfoDeleteDialogComponent {
    eInvoiceInfo: IEInvoiceInfo;

    constructor(
        private eInvoiceInfoService: EInvoiceInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.eInvoiceInfoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'eInvoiceInfoListModification',
                content: 'Deleted an eInvoiceInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-e-invoice-info-delete-popup',
    template: ''
})
export class EInvoiceInfoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ eInvoiceInfo }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(EInvoiceInfoDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.eInvoiceInfo = eInvoiceInfo;
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
