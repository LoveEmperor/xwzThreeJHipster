/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InvoiceprojectTestModule } from '../../../test.module';
import { InvoiceInfoDetailComponent } from 'app/entities/invoice-info/invoice-info-detail.component';
import { InvoiceInfo } from 'app/shared/model/invoice-info.model';

describe('Component Tests', () => {
    describe('InvoiceInfo Management Detail Component', () => {
        let comp: InvoiceInfoDetailComponent;
        let fixture: ComponentFixture<InvoiceInfoDetailComponent>;
        const route = ({ data: of({ invoiceInfo: new InvoiceInfo(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [InvoiceInfoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(InvoiceInfoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(InvoiceInfoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.invoiceInfo).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
