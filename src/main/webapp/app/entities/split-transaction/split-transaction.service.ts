import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISplitTransaction } from 'app/shared/model/split-transaction.model';

type EntityResponseType = HttpResponse<ISplitTransaction>;
type EntityArrayResponseType = HttpResponse<ISplitTransaction[]>;

@Injectable({ providedIn: 'root' })
export class SplitTransactionService {
    private resourceUrl = SERVER_API_URL + 'api/split-transactions';

    constructor(private http: HttpClient) {}

    create(splitTransaction: ISplitTransaction): Observable<EntityResponseType> {
        return this.http.post<ISplitTransaction>(this.resourceUrl, splitTransaction, { observe: 'response' });
    }

    update(splitTransaction: ISplitTransaction): Observable<EntityResponseType> {
        return this.http.put<ISplitTransaction>(this.resourceUrl, splitTransaction, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISplitTransaction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISplitTransaction[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
