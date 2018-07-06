/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FinancialdashboardTestModule } from '../../../test.module';
import { ReportingCategoryDetailComponent } from 'app/entities/reporting-category/reporting-category-detail.component';
import { ReportingCategory } from 'app/shared/model/reporting-category.model';

describe('Component Tests', () => {
    describe('ReportingCategory Management Detail Component', () => {
        let comp: ReportingCategoryDetailComponent;
        let fixture: ComponentFixture<ReportingCategoryDetailComponent>;
        const route = ({ data: of({ reportingCategory: new ReportingCategory(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [ReportingCategoryDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ReportingCategoryDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReportingCategoryDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.reportingCategory).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
