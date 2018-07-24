/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InvoiceprojectTestModule } from '../../../test.module';
import { EInvoiceInfoDeleteDialogComponent } from 'app/entities/e-invoice-info/e-invoice-info-delete-dialog.component';
import { EInvoiceInfoService } from 'app/entities/e-invoice-info/e-invoice-info.service';

describe('Component Tests', () => {
    describe('EInvoiceInfo Management Delete Component', () => {
        let comp: EInvoiceInfoDeleteDialogComponent;
        let fixture: ComponentFixture<EInvoiceInfoDeleteDialogComponent>;
        let service: EInvoiceInfoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [EInvoiceInfoDeleteDialogComponent]
            })
                .overrideTemplate(EInvoiceInfoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EInvoiceInfoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EInvoiceInfoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
