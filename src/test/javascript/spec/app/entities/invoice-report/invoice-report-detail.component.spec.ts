/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InvoiceprojectTestModule } from '../../../test.module';
import { InvoiceReportDetailComponent } from 'app/entities/invoice-report/invoice-report-detail.component';
import { InvoiceReport } from 'app/shared/model/invoice-report.model';

describe('Component Tests', () => {
    describe('InvoiceReport Management Detail Component', () => {
        let comp: InvoiceReportDetailComponent;
        let fixture: ComponentFixture<InvoiceReportDetailComponent>;
        const route = ({ data: of({ invoiceReport: new InvoiceReport(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [InvoiceReportDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InvoiceReportDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InvoiceReportDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.invoiceReport).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
