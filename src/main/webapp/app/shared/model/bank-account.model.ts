import { ITransactionIng } from 'app/shared/model//transaction-ing.model';

export interface IBankAccount {
    id?: number;
    name?: string;
    bank?: string;
    iban?: string;
    goal?: string;
    transactionIngs?: ITransactionIng[];
}

export class BankAccount implements IBankAccount {
    constructor(
        public id?: number,
        public name?: string,
        public bank?: string,
        public iban?: string,
        public goal?: string,
        public transactionIngs?: ITransactionIng[]
    ) {}
}
