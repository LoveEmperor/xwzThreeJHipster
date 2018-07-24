import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInvoiceInfo } from 'app/shared/model/invoice-info.model';

type EntityResponseType = HttpResponse<IInvoiceInfo>;
type EntityArrayResponseType = HttpResponse<IInvoiceInfo[]>;

@Injectable({ providedIn: 'root' })
export class InvoiceInfoService {
    private resourceUrl = SERVER_API_URL + 'api/invoice-infos';

    constructor(private http: HttpClient) {}

    create(invoiceInfo: IInvoiceInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(invoiceInfo);
        return this.http
            .post<IInvoiceInfo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(invoiceInfo: IInvoiceInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(invoiceInfo);
        return this.http
            .put<IInvoiceInfo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInvoiceInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInvoiceInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(invoiceInfo: IInvoiceInfo): IInvoiceInfo {
        const copy: IInvoiceInfo = Object.assign({}, invoiceInfo, {
            createTime: invoiceInfo.createTime != null && invoiceInfo.createTime.isValid() ? invoiceInfo.createTime.toJSON() : null,
            updateTime: invoiceInfo.updateTime != null && invoiceInfo.updateTime.isValid() ? invoiceInfo.updateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
        res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((invoiceInfo: IInvoiceInfo) => {
            invoiceInfo.createTime = invoiceInfo.createTime != null ? moment(invoiceInfo.createTime) : null;
            invoiceInfo.updateTime = invoiceInfo.updateTime != null ? moment(invoiceInfo.updateTime) : null;
        });
        return res;
    }
}
