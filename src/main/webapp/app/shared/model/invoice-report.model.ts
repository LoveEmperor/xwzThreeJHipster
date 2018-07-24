import { Moment } from 'moment';

export interface IInvoiceReport {
    id?: number;
    fs?: string;
    jehj?: string;
    sehj?: string;
    jshj?: string;
    nsrsum?: string;
    createTime?: Moment;
    updateTime?: Moment;
}

export class InvoiceReport implements IInvoiceReport {
    constructor(
        public id?: number,
        public fs?: string,
        public jehj?: string,
        public sehj?: string,
        public jshj?: string,
        public nsrsum?: string,
        public createTime?: Moment,
        public updateTime?: Moment
    ) {}
}
