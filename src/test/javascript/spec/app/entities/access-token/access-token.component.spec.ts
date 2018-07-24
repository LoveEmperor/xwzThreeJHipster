/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InvoiceprojectTestModule } from '../../../test.module';
import { AccessTokenComponent } from 'app/entities/access-token/access-token.component';
import { AccessTokenService } from 'app/entities/access-token/access-token.service';
import { AccessToken } from 'app/shared/model/access-token.model';

describe('Component Tests', () => {
    describe('AccessToken Management Component', () => {
        let comp: AccessTokenComponent;
        let fixture: ComponentFixture<AccessTokenComponent>;
        let service: AccessTokenService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InvoiceprojectTestModule],
                declarations: [AccessTokenComponent],
                providers: []
            })
                .overrideTemplate(AccessTokenComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AccessTokenComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AccessTokenService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new AccessToken(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.accessTokens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
