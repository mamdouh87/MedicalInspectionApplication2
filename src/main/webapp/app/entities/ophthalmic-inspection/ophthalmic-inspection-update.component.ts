import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IOphthalmicInspection, OphthalmicInspection } from 'app/shared/model/ophthalmic-inspection.model';
import { OphthalmicInspectionService } from './ophthalmic-inspection.service';
import { IMedicalCondition } from 'app/shared/model/medical-condition.model';
import { MedicalConditionService } from 'app/entities/medical-condition/medical-condition.service';
import { IInspectionResult } from 'app/shared/model/inspection-result.model';
import { InspectionResultService } from 'app/entities/inspection-result/inspection-result.service';

type SelectableEntity = IMedicalCondition | IInspectionResult;

@Component({
  selector: 'jhi-ophthalmic-inspection-update',
  templateUrl: './ophthalmic-inspection-update.component.html',
})
export class OphthalmicInspectionUpdateComponent implements OnInit {
  isSaving = false;
  medicalconditions: IMedicalCondition[] = [];
  inspectionresults: IInspectionResult[] = [];

  editForm = this.fb.group({
    id: [],
    rigthEye: [],
    leftEye: [],
    doctorComments: [],
    medicalConditionId: [],
    inspectionResultId: [],
  });

  constructor(
    protected ophthalmicInspectionService: OphthalmicInspectionService,
    protected medicalConditionService: MedicalConditionService,
    protected inspectionResultService: InspectionResultService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ophthalmicInspection }) => {
      this.updateForm(ophthalmicInspection);

      this.medicalConditionService
        .query({ filter: 'ophthalmicinspection-is-null' })
        .pipe(
          map((res: HttpResponse<IMedicalCondition[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IMedicalCondition[]) => {
          if (!ophthalmicInspection.medicalConditionId) {
            this.medicalconditions = resBody;
          } else {
            this.medicalConditionService
              .find(ophthalmicInspection.medicalConditionId)
              .pipe(
                map((subRes: HttpResponse<IMedicalCondition>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IMedicalCondition[]) => (this.medicalconditions = concatRes));
          }
        });

      this.inspectionResultService
        .query({ filter: 'ophthalmicinspection-is-null' })
        .pipe(
          map((res: HttpResponse<IInspectionResult[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInspectionResult[]) => {
          if (!ophthalmicInspection.inspectionResultId) {
            this.inspectionresults = resBody;
          } else {
            this.inspectionResultService
              .find(ophthalmicInspection.inspectionResultId)
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

  updateForm(ophthalmicInspection: IOphthalmicInspection): void {
    this.editForm.patchValue({
      id: ophthalmicInspection.id,
      rigthEye: ophthalmicInspection.rigthEye,
      leftEye: ophthalmicInspection.leftEye,
      doctorComments: ophthalmicInspection.doctorComments,
      medicalConditionId: ophthalmicInspection.medicalConditionId,
      inspectionResultId: ophthalmicInspection.inspectionResultId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ophthalmicInspection = this.createFromForm();
    if (ophthalmicInspection.id !== undefined) {
      this.subscribeToSaveResponse(this.ophthalmicInspectionService.update(ophthalmicInspection));
    } else {
      this.subscribeToSaveResponse(this.ophthalmicInspectionService.create(ophthalmicInspection));
    }
  }

  private createFromForm(): IOphthalmicInspection {
    return {
      ...new OphthalmicInspection(),
      id: this.editForm.get(['id'])!.value,
      rigthEye: this.editForm.get(['rigthEye'])!.value,
      leftEye: this.editForm.get(['leftEye'])!.value,
      doctorComments: this.editForm.get(['doctorComments'])!.value,
      medicalConditionId: this.editForm.get(['medicalConditionId'])!.value,
      inspectionResultId: this.editForm.get(['inspectionResultId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOphthalmicInspection>>): void {
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
