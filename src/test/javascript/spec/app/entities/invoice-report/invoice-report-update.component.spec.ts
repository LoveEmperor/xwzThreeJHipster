/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InvoiceprojectTestModule } from '../../../test.module';
import { InvoiceReportUpdateComponent } from 'app/entities/invoice-report/invoice-report-update.component';
import { InvoiceReportService } from 'app/entities/invoice-report/invoice-report.service';
import { InvoiceReport } from 'app/shared/model/invoice-report.model';

describe('Component Tests', () => {
    describe('InvoiceReport Management Update Component', () => {
        let comp: InvoiceReportUpdateComponent;
        let fixture: ComponentFixture<InvoiceReportUpdateComponent>;
        let service: InvoiceReportService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [InvoiceReportUpdateComponent]
            })
                .overrideTemplate(InvoiceReportUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InvoiceReportUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InvoiceReportService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new InvoiceReport(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.invoiceReport = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new InvoiceReport();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.invoiceReport = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
