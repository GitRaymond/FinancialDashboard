/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FinancialdashboardTestModule } from '../../../test.module';
import { ReportingCategoryDeleteDialogComponent } from 'app/entities/reporting-category/reporting-category-delete-dialog.component';
import { ReportingCategoryService } from 'app/entities/reporting-category/reporting-category.service';

describe('Component Tests', () => {
    describe('ReportingCategory Management Delete Component', () => {
        let comp: ReportingCategoryDeleteDialogComponent;
        let fixture: ComponentFixture<ReportingCategoryDeleteDialogComponent>;
        let service: ReportingCategoryService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FinancialdashboardTestModule],
                declarations: [ReportingCategoryDeleteDialogComponent]
            })
                .overrideTemplate(ReportingCategoryDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReportingCategoryDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReportingCategoryService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
