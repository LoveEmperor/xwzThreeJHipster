/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InvoiceprojectTestModule } from '../../../test.module';
import { InvoiceInfoDeleteDialogComponent } from 'app/entities/invoice-info/invoice-info-delete-dialog.component';
import { InvoiceInfoService } from 'app/entities/invoice-info/invoice-info.service';

describe('Component Tests', () => {
    describe('InvoiceInfo Management Delete Component', () => {
        let comp: InvoiceInfoDeleteDialogComponent;
        let fixture: ComponentFixture<InvoiceInfoDeleteDialogComponent>;
        let service: InvoiceInfoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [InvoiceInfoDeleteDialogComponent]
            })
                .overrideTemplate(InvoiceInfoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InvoiceInfoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InvoiceInfoService);
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
