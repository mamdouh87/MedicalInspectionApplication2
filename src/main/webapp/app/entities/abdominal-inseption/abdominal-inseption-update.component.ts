import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IAbdominalInseption, AbdominalInseption } from 'app/shared/model/abdominal-inseption.model';
import { AbdominalInseptionService } from './abdominal-inseption.service';
import { IMedicalCondition } from 'app/shared/model/medical-condition.model';
import { MedicalConditionService } from 'app/entities/medical-condition/medical-condition.service';
import { IInspectionResult } from 'app/shared/model/inspection-result.model';
import { InspectionResultService } from 'app/entities/inspection-result/inspection-result.service';

type SelectableEntity = IMedicalCondition | IInspectionResult;

@Component({
  selector: 'jhi-abdominal-inseption-update',
  templateUrl: './abdominal-inseption-update.component.html',
})
export class AbdominalInseptionUpdateComponent implements OnInit {
  isSaving = false;
  medicalconditions: IMedicalCondition[] = [];
  inspectionresults: IInspectionResult[] = [];

  editForm = this.fb.group({
    id: [],
    bloodGroup: [],
    doctorComments: [],
    medicalConditionId: [],
    inspectionResultId: [],
  });

  constructor(
    protected abdominalInseptionService: AbdominalInseptionService,
    protected medicalConditionService: MedicalConditionService,
    protected inspectionResultService: InspectionResultService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ abdominalInseption }) => {
      this.updateForm(abdominalInseption);

      this.medicalConditionService
        .query({ filter: 'abdominalinseption-is-null' })
        .pipe(
          map((res: HttpResponse<IMedicalCondition[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IMedicalCondition[]) => {
          if (!abdominalInseption.medicalConditionId) {
            this.medicalconditions = resBody;
          } else {
            this.medicalConditionService
              .find(abdominalInseption.medicalConditionId)
              .pipe(
                map((subRes: HttpResponse<IMedicalCondition>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IMedicalCondition[]) => (this.medicalconditions = concatRes));
          }
        });

      this.inspectionResultService
        .query({ filter: 'abdominalinseption-is-null' })
        .pipe(
          map((res: HttpResponse<IInspectionResult[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInspectionResult[]) => {
          if (!abdominalInseption.inspectionResultId) {
            this.inspectionresults = resBody;
          } else {
            this.inspectionResultService
              .find(abdominalInseption.inspectionResultId)
              .pipe(
                map((subRes: HttpResponse<IInspectionResult>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInspectionResult[]) => (this.inspectionresults = concatRes));
          }
        });
    });
  }

  updateForm(abdominalInseption: IAbdominalInseption): void {
    this.editForm.patchValue({
      id: abdominalInseption.id,
      bloodGroup: abdominalInseption.bloodGroup,
      doctorComments: abdominalInseption.doctorComments,
      medicalConditionId: abdominalInseption.medicalConditionId,
      inspectionResultId: abdominalInseption.inspectionResultId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const abdominalInseption = this.createFromForm();
    if (abdominalInseption.id !== undefined) {
      this.subscribeToSaveResponse(this.abdominalInseptionService.update(abdominalInseption));
    } else {
      this.subscribeToSaveResponse(this.abdominalInseptionService.create(abdominalInseption));
    }
  }

  private createFromForm(): IAbdominalInseption {
    return {
      ...new AbdominalInseption(),
      id: this.editForm.get(['id'])!.value,
      bloodGroup: this.editForm.get(['bloodGroup'])!.value,
      doctorComments: this.editForm.get(['doctorComments'])!.value,
      medicalConditionId: this.editForm.get(['medicalConditionId'])!.value,
      inspectionResultId: this.editForm.get(['inspectionResultId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAbdominalInseption>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
