/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InvoiceprojectTestModule } from '../../../test.module';
import { InvoiceInfoUpdateComponent } from 'app/entities/invoice-info/invoice-info-update.component';
import { InvoiceInfoService } from 'app/entities/invoice-info/invoice-info.service';
import { InvoiceInfo } from 'app/shared/model/invoice-info.model';

describe('Component Tests', () => {
    describe('InvoiceInfo Management Update Component', () => {
        let comp: InvoiceInfoUpdateComponent;
        let fixture: ComponentFixture<InvoiceInfoUpdateComponent>;
        let service: InvoiceInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [InvoiceInfoUpdateComponent]
            })
                .overrideTemplate(InvoiceInfoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(InvoiceInfoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InvoiceInfoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new InvoiceInfo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.invoiceInfo = entity;
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
                    const entity = new InvoiceInfo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.invoiceInfo = entity;
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
