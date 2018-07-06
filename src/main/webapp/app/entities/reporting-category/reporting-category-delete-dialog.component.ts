import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IReportingCategory } from 'app/shared/model/reporting-category.model';
import { ReportingCategoryService } from './reporting-category.service';

@Component({
    selector: 'jhi-reporting-category-delete-dialog',
    templateUrl: './reporting-category-delete-dialog.component.html'
})
export class ReportingCategoryDeleteDialogComponent {
    reportingCategory: IReportingCategory;

    constructor(
        private reportingCategoryService: ReportingCategoryService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.reportingCategoryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'reportingCategoryListModification',
                content: 'Deleted an reportingCategory'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-reporting-category-delete-popup',
    template: ''
})
export class ReportingCategoryDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reportingCategory }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ReportingCategoryDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.reportingCategory = reportingCategory;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
