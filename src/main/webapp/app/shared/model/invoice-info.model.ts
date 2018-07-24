import { Moment } from 'moment';

export interface IInvoiceInfo {
    id?: number;
    kpTime?: string;
    kpStatus?: string;
    kpMoney?: number;
    titleType?: string;
    invoiceTitle?: string;
    qydutyNum?: string;
    fpContent?: string;
    email?: string;
    phone?: string;
    payRecordid?: string;
    invguId?: string;
    fpdm?: string;
    fphm?: string;
    kpr?: string;
    kprId?: string;
    reson?: string;
    createTime?: Moment;
    updateTime?: Moment;
    extractFphm?: string;
    extractId?: number;
}

export class InvoiceInfo implements IInvoiceInfo {
    constructor(
        public id?: number,
        public kpTime?: string,
        public kpStatus?: string,
        public kpMoney?: number,
        public titleType?: string,
        public invoiceTitle?: string,
        public qydutyNum?: string,
        public fpContent?: string,
        public email?: string,
        public phone?: string,
        public payRecordid?: string,
        public invguId?: string,
        public fpdm?: string,
        public fphm?: string,
        public kpr?: string,
        public kprId?: string,
        public reson?: string,
        public createTime?: Moment,
        public updateTime?: Moment,
        public extractFphm?: string,
        public extractId?: number
    ) {}
}
