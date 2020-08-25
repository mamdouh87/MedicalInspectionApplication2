import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IMedicalCondition, MedicalCondition } from 'app/shared/model/medical-condition.model';
import { MedicalConditionService } from './medical-condition.service';
import { IInspectionType } from 'app/shared/model/inspection-type.model';
import { InspectionTypeService } from 'app/entities/inspection-type/inspection-type.service';

@Component({
  selector: 'jhi-medical-condition-update',
  templateUrl: './medical-condition-update.component.html',
})
export class MedicalConditionUpdateComponent implements OnInit {
  isSaving = false;
  inspectiontypes: IInspectionType[] = [];

  editForm = this.fb.group({
    id: [],
    conditionNameEn: [],
    conditionNameAr: [],
    inspectionTypeId: [],
  });

  constructor(
    protected medicalConditionService: MedicalConditionService,
    protected inspectionTypeService: InspectionTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalCondition }) => {
      this.updateForm(medicalCondition);

      this.inspectionTypeService
        .query({ filter: 'medicalcondition-is-null' })
        .pipe(
          map((res: HttpResponse<IInspectionType[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInspectionType[]) => {
          if (!medicalCondition.inspectionTypeId) {
            this.inspectiontypes = resBody;
          } else {
            this.inspectionTypeService
              .find(medicalCondition.inspectionTypeId)
              .pipe(
                map((subRes: HttpResponse<IInspectionType>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInspectionType[]) => (this.inspectiontypes = concatRes));
          }
        });
    });
  }

  updateForm(medicalCondition: IMedicalCondition): void {
    this.editForm.patchValue({
      id: medicalCondition.id,
      conditionNameEn: medicalCondition.conditionNameEn,
      conditionNameAr: medicalCondition.conditionNameAr,
      inspectionTypeId: medicalCondition.inspectionTypeId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const medicalCondition = this.createFromForm();
    if (medicalCondition.id !== undefined) {
      this.subscribeToSaveResponse(this.medicalConditionService.update(medicalCondition));
    } else {
      this.subscribeToSaveResponse(this.medicalConditionService.create(medicalCondition));
    }
  }

  private createFromForm(): IMedicalCondition {
    return {
      ...new MedicalCondition(),
      id: this.editForm.get(['id'])!.value,
      conditionNameEn: this.editForm.get(['conditionNameEn'])!.value,
      conditionNameAr: this.editForm.get(['conditionNameAr'])!.value,
      inspectionTypeId: this.editForm.get(['inspectionTypeId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMedicalCondition>>): void {
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

  trackById(index: number, item: IInspectionType): any {
    return item.id;
  }
}
