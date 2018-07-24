/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InvoiceprojectTestModule } from '../../../test.module';
import { InvoiceInfoComponent } from 'app/entities/invoice-info/invoice-info.component';
import { InvoiceInfoService } from 'app/entities/invoice-info/invoice-info.service';
import { InvoiceInfo } from 'app/shared/model/invoice-info.model';

describe('Component Tests', () => {
    describe('InvoiceInfo Management Component', () => {
        let comp: InvoiceInfoComponent;
        let fixture: ComponentFixture<InvoiceInfoComponent>;
        let service: InvoiceInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [InvoiceInfoComponent],
                providers: []
            })
                .overrideTemplate(InvoiceInfoComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InvoiceInfoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InvoiceInfoService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new InvoiceInfo(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.invoiceInfos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
