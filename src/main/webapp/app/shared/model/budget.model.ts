export interface IBudget {
    id?: number;
    year?: number;
    month?: number;
    amount?: number;
    reportingCategoryId?: number;
}

export class Budget implements IBudget {
    constructor(
        public id?: number,
        public year?: number,
        public month?: number,
        public amount?: number,
        public reportingCategoryId?: number
    ) {}
}
