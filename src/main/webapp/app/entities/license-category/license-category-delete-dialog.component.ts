import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILicenseCategory } from 'app/shared/model/license-category.model';
import { LicenseCategoryService } from './license-category.service';

@Component({
  templateUrl: './license-category-delete-dialog.component.html',
})
export class LicenseCategoryDeleteDialogComponent {
  licenseCategory?: ILicenseCategory;

  constructor(
    protected licenseCategoryService: LicenseCategoryService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.licenseCategoryService.delete(id).subscribe(() => {
      this.eventManager.broadcast('licenseCategoryListModification');
      this.activeModal.close();
    });
  }
}
