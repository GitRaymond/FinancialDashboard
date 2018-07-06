import { ITag } from 'app/shared/model//tag.model';
import { ISplitTransaction } from 'app/shared/model//split-transaction.model';

export const enum Sign {
    PLUS = 'PLUS',
    MINUS = 'MINUS'
}

export interface ITransactionIng {
    id?: number;
    date?: number;
    name?: string;
    myBankAccount?: string;
    contraBankAccount?: string;
    code?: string;
    sign?: Sign;
    amount?: number;
    mutation?: string;
    descriptionContentType?: string;
    description?: any;
    vendorId?: number;
    bankAccountId?: number;
    reportingCategoryId?: number;
    tags?: ITag[];
    splitTransactions?: ISplitTransaction[];
}

export class TransactionIng implements ITransactionIng {
    constructor(
        public id?: number,
        public date?: number,
        public name?: string,
        public myBankAccount?: string,
        public contraBankAccount?: string,
        public code?: string,
        public sign?: Sign,
        public amount?: number,
        public mutation?: string,
        public descriptionContentType?: string,
        public description?: any,
        public vendorId?: number,
        public bankAccountId?: number,
        public reportingCategoryId?: number,
        public tags?: ITag[],
        public splitTransactions?: ISplitTransaction[]
    ) {}
}
