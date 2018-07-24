import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IInvoiceReport } from 'app/shared/model/invoice-report.model';

type EntityResponseType = HttpResponse<IInvoiceReport>;
type EntityArrayResponseType = HttpResponse<IInvoiceReport[]>;

@Injectable({ providedIn: 'root' })
export class InvoiceReportService {
    private resourceUrl = SERVER_API_URL + 'api/invoice-reports';

    constructor(private http: HttpClient) {}

    create(invoiceReport: IInvoiceReport): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(invoiceReport);
        return this.http
            .post<IInvoiceReport>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(invoiceReport: IInvoiceReport): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(invoiceReport);
        return this.http
            .put<IInvoiceReport>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IInvoiceReport>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IInvoiceReport[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(invoiceReport: IInvoiceReport): IInvoiceReport {
        const copy: IInvoiceReport = Object.assign({}, invoiceReport, {
            createTime: invoiceReport.createTime != null && invoiceReport.createTime.isValid() ? invoiceReport.createTime.toJSON() : null,
            updateTime: invoiceReport.updateTime != null && invoiceReport.updateTime.isValid() ? invoiceReport.updateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.createTime = res.body.createTime != null ? moment(res.body.createTime) : null;
        res.body.updateTime = res.body.updateTime != null ? moment(res.body.updateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((invoiceReport: IInvoiceReport) => {
            invoiceReport.createTime = invoiceReport.createTime != null ? moment(invoiceReport.createTime) : null;
            invoiceReport.updateTime = invoiceReport.updateTime != null ? moment(invoiceReport.updateTime) : null;
        });
        return res;
    }
}
