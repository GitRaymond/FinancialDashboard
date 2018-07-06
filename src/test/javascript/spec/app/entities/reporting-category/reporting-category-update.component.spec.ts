/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FinancialdashboardTestModule } from '../../../test.module';
import { ReportingCategoryUpdateComponent } from 'app/entities/reporting-category/reporting-category-update.component';
import { ReportingCategoryService } from 'app/entities/reporting-category/reporting-category.service';
import { ReportingCategory } from 'app/shared/model/reporting-category.model';

describe('Component Tests', () => {
    describe('ReportingCategory Management Update Component', () => {
        let comp: ReportingCategoryUpdateComponent;
        let fixture: ComponentFixture<ReportingCategoryUpdateComponent>;
        let service: ReportingCategoryService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [ReportingCategoryUpdateComponent]
            })
                .overrideTemplate(ReportingCategoryUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReportingCategoryUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReportingCategoryService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ReportingCategory(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.reportingCategory = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ReportingCategory();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.reportingCategory = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
