/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InvoiceprojectTestModule } from '../../../test.module';
import { EInvoiceInfoDetailComponent } from 'app/entities/e-invoice-info/e-invoice-info-detail.component';
import { EInvoiceInfo } from 'app/shared/model/e-invoice-info.model';

describe('Component Tests', () => {
    describe('EInvoiceInfo Management Detail Component', () => {
        let comp: EInvoiceInfoDetailComponent;
        let fixture: ComponentFixture<EInvoiceInfoDetailComponent>;
        const route = ({ data: of({ eInvoiceInfo: new EInvoiceInfo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [EInvoiceInfoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(EInvoiceInfoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(EInvoiceInfoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.eInvoiceInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
