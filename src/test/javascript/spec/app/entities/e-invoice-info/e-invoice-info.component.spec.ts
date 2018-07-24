/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InvoiceprojectTestModule } from '../../../test.module';
import { EInvoiceInfoComponent } from 'app/entities/e-invoice-info/e-invoice-info.component';
import { EInvoiceInfoService } from 'app/entities/e-invoice-info/e-invoice-info.service';
import { EInvoiceInfo } from 'app/shared/model/e-invoice-info.model';

describe('Component Tests', () => {
    describe('EInvoiceInfo Management Component', () => {
        let comp: EInvoiceInfoComponent;
        let fixture: ComponentFixture<EInvoiceInfoComponent>;
        let service: EInvoiceInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [EInvoiceInfoComponent],
                providers: []
            })
                .overrideTemplate(EInvoiceInfoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EInvoiceInfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EInvoiceInfoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EInvoiceInfo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.eInvoiceInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
