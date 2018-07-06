export const enum Sign {
    PLUS = 'PLUS',
    MINUS = 'MINUS'
}

export interface ISplitTransaction {
    id?: number;
    splitName?: string;
    amount?: number;
    sign?: Sign;
    transactionIngId?: number;
}

export class SplitTransaction implements ISplitTransaction {
    constructor(
        public id?: number,
        public splitName?: string,
        public amount?: number,
        public sign?: Sign,
        public transactionIngId?: number
    ) {}
}
