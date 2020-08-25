import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInspectionResult, InspectionResult } from 'app/shared/model/inspection-result.model';
import { InspectionResultService } from './inspection-result.service';

@Component({
  selector: 'jhi-inspection-result-update',
  templateUrl: './inspection-result-update.component.html',
})
export class InspectionResultUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameEn: [],
    nameAr: [],
    code: [],
  });

  constructor(
    protected inspectionResultService: InspectionResultService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inspectionResult }) => {
      this.updateForm(inspectionResult);
    });
  }

  updateForm(inspectionResult: IInspectionResult): void {
    this.editForm.patchValue({
      id: inspectionResult.id,
      nameEn: inspectionResult.nameEn,
      nameAr: inspectionResult.nameAr,
      code: inspectionResult.code,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inspectionResult = this.createFromForm();
    if (inspectionResult.id !== undefined) {
      this.subscribeToSaveResponse(this.inspectionResultService.update(inspectionResult));
    } else {
      this.subscribeToSaveResponse(this.inspectionResultService.create(inspectionResult));
    }
  }

  private createFromForm(): IInspectionResult {
    return {
      ...new InspectionResult(),
      id: this.editForm.get(['id'])!.value,
      nameEn: this.editForm.get(['nameEn'])!.value,
      nameAr: this.editForm.get(['nameAr'])!.value,
      code: this.editForm.get(['code'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInspectionResult>>): void {
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
