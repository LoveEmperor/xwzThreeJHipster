/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InvoiceprojectTestModule } from '../../../test.module';
import { EInvoiceInfoUpdateComponent } from 'app/entities/e-invoice-info/e-invoice-info-update.component';
import { EInvoiceInfoService } from 'app/entities/e-invoice-info/e-invoice-info.service';
import { EInvoiceInfo } from 'app/shared/model/e-invoice-info.model';

describe('Component Tests', () => {
    describe('EInvoiceInfo Management Update Component', () => {
        let comp: EInvoiceInfoUpdateComponent;
        let fixture: ComponentFixture<EInvoiceInfoUpdateComponent>;
        let service: EInvoiceInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [EInvoiceInfoUpdateComponent]
            })
                .overrideTemplate(EInvoiceInfoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EInvoiceInfoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EInvoiceInfoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EInvoiceInfo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.eInvoiceInfo = entity;
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
                    const entity = new EInvoiceInfo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.eInvoiceInfo = entity;
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
