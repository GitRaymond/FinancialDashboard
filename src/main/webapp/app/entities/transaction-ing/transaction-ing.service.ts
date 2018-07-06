import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITransactionIng } from 'app/shared/model/transaction-ing.model';

type EntityResponseType = HttpResponse<ITransactionIng>;
type EntityArrayResponseType = HttpResponse<ITransactionIng[]>;

@Injectable({ providedIn: 'root' })
export class TransactionIngService {
    private resourceUrl = SERVER_API_URL + 'api/transaction-ings';

    constructor(private http: HttpClient) {}

    create(transactionIng: ITransactionIng): Observable<EntityResponseType> {
        return this.http.post<ITransactionIng>(this.resourceUrl, transactionIng, { observe: 'response' });
    }

    update(transactionIng: ITransactionIng): Observable<EntityResponseType> {
        return this.http.put<ITransactionIng>(this.resourceUrl, transactionIng, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITransactionIng>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITransactionIng[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
