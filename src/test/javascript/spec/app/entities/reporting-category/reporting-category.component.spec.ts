/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { FinancialdashboardTestModule } from '../../../test.module';
import { ReportingCategoryComponent } from 'app/entities/reporting-category/reporting-category.component';
import { ReportingCategoryService } from 'app/entities/reporting-category/reporting-category.service';
import { ReportingCategory } from 'app/shared/model/reporting-category.model';

describe('Component Tests', () => {
    describe('ReportingCategory Management Component', () => {
        let comp: ReportingCategoryComponent;
        let fixture: ComponentFixture<ReportingCategoryComponent>;
        let service: ReportingCategoryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [ReportingCategoryComponent],
                providers: []
            })
                .overrideTemplate(ReportingCategoryComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReportingCategoryComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReportingCategoryService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ReportingCategory(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.reportingCategories[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
