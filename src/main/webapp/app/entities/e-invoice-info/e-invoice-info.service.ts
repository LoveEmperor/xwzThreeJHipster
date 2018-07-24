import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEInvoiceInfo } from 'app/shared/model/e-invoice-info.model';

type EntityResponseType = HttpResponse<IEInvoiceInfo>;
type EntityArrayResponseType = HttpResponse<IEInvoiceInfo[]>;

@Injectable({ providedIn: 'root' })
export class EInvoiceInfoService {
    private resourceUrl = SERVER_API_URL + 'api/e-invoice-infos';

    constructor(private http: HttpClient) {}

    create(eInvoiceInfo: IEInvoiceInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(eInvoiceInfo);
        return this.http
            .post<IEInvoiceInfo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(eInvoiceInfo: IEInvoiceInfo): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(eInvoiceInfo);
        return this.http
            .put<IEInvoiceInfo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IEInvoiceInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IEInvoiceInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(eInvoiceInfo: IEInvoiceInfo): IEInvoiceInfo {
        const copy: IEInvoiceInfo = Object.assign({}, eInvoiceInfo, {
            createTime: eInvoiceInfo.createTime != null && eInvoiceInfo.createTime.isValid() ? eInvoiceInfo.createTime.toJSON() : null,
            updateTime: eInvoiceInfo.updateTime != null && eInvoiceInfo.updateTime.isValid() ? eInvoiceInfo.updateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
        res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((eInvoiceInfo: IEInvoiceInfo) => {
            eInvoiceInfo.createTime = eInvoiceInfo.createTime != null ? moment(eInvoiceInfo.createTime) : null;
            eInvoiceInfo.updateTime = eInvoiceInfo.updateTime != null ? moment(eInvoiceInfo.updateTime) : null;
        });
        return res;
    }
}
