import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILicenseCategory, LicenseCategory } from 'app/shared/model/license-category.model';
import { LicenseCategoryService } from './license-category.service';

@Component({
  selector: 'jhi-license-category-update',
  templateUrl: './license-category-update.component.html',
})
export class LicenseCategoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameEn: [],
    nameAr: [],
    code: [],
  });

  constructor(
    protected licenseCategoryService: LicenseCategoryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ licenseCategory }) => {
      this.updateForm(licenseCategory);
    });
  }

  updateForm(licenseCategory: ILicenseCategory): void {
    this.editForm.patchValue({
      id: licenseCategory.id,
      nameEn: licenseCategory.nameEn,
      nameAr: licenseCategory.nameAr,
      code: licenseCategory.code,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const licenseCategory = this.createFromForm();
    if (licenseCategory.id !== undefined) {
      this.subscribeToSaveResponse(this.licenseCategoryService.update(licenseCategory));
    } else {
      this.subscribeToSaveResponse(this.licenseCategoryService.create(licenseCategory));
    }
  }

  private createFromForm(): ILicenseCategory {
    return {
      ...new LicenseCategory(),
      id: this.editForm.get(['id'])!.value,
      nameEn: this.editForm.get(['nameEn'])!.value,
      nameAr: this.editForm.get(['nameAr'])!.value,
      code: this.editForm.get(['code'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILicenseCategory>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
