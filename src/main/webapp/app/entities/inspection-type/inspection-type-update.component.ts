import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInspectionType, InspectionType } from 'app/shared/model/inspection-type.model';
import { InspectionTypeService } from './inspection-type.service';

@Component({
  selector: 'jhi-inspection-type-update',
  templateUrl: './inspection-type-update.component.html',
})
export class InspectionTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameEn: [],
    nameAr: [],
    code: [],
  });

  constructor(protected inspectionTypeService: InspectionTypeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inspectionType }) => {
      this.updateForm(inspectionType);
    });
  }

  updateForm(inspectionType: IInspectionType): void {
    this.editForm.patchValue({
      id: inspectionType.id,
      nameEn: inspectionType.nameEn,
      nameAr: inspectionType.nameAr,
      code: inspectionType.code,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inspectionType = this.createFromForm();
    if (inspectionType.id !== undefined) {
      this.subscribeToSaveResponse(this.inspectionTypeService.update(inspectionType));
    } else {
      this.subscribeToSaveResponse(this.inspectionTypeService.create(inspectionType));
    }
  }

  private createFromForm(): IInspectionType {
    return {
      ...new InspectionType(),
      id: this.editForm.get(['id'])!.value,
      nameEn: this.editForm.get(['nameEn'])!.value,
      nameAr: this.editForm.get(['nameAr'])!.value,
      code: this.editForm.get(['code'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInspectionType>>): void {
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
