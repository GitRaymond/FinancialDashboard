import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReportingCategory } from 'app/shared/model/reporting-category.model';

type EntityResponseType = HttpResponse<IReportingCategory>;
type EntityArrayResponseType = HttpResponse<IReportingCategory[]>;

@Injectable({ providedIn: 'root' })
export class ReportingCategoryService {
    private resourceUrl = SERVER_API_URL + 'api/reporting-categories';

    constructor(private http: HttpClient) {}

    create(reportingCategory: IReportingCategory): Observable<EntityResponseType> {
        return this.http.post<IReportingCategory>(this.resourceUrl, reportingCategory, { observe: 'response' });
    }

    update(reportingCategory: IReportingCategory): Observable<EntityResponseType> {
        return this.http.put<IReportingCategory>(this.resourceUrl, reportingCategory, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IReportingCategory>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IReportingCategory[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
