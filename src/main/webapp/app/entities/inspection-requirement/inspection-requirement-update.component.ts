import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInspectionRequirement, InspectionRequirement } from 'app/shared/model/inspection-requirement.model';
import { InspectionRequirementService } from './inspection-requirement.service';

@Component({
  selector: 'jhi-inspection-requirement-update',
  templateUrl: './inspection-requirement-update.component.html',
})
export class InspectionRequirementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameEn: [],
    nameAr: [],
    code: [],
    order: [],
  });

  constructor(
    protected inspectionRequirementService: InspectionRequirementService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inspectionRequirement }) => {
      this.updateForm(inspectionRequirement);
    });
  }

  updateForm(inspectionRequirement: IInspectionRequirement): void {
    this.editForm.patchValue({
      id: inspectionRequirement.id,
      nameEn: inspectionRequirement.nameEn,
      nameAr: inspectionRequirement.nameAr,
      code: inspectionRequirement.code,
      order: inspectionRequirement.order,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const inspectionRequirement = this.createFromForm();
    if (inspectionRequirement.id !== undefined) {
      this.subscribeToSaveResponse(this.inspectionRequirementService.update(inspectionRequirement));
    } else {
      this.subscribeToSaveResponse(this.inspectionRequirementService.create(inspectionRequirement));
    }
  }

  private createFromForm(): IInspectionRequirement {
    return {
      ...new InspectionRequirement(),
      id: this.editForm.get(['id'])!.value,
      nameEn: this.editForm.get(['nameEn'])!.value,
      nameAr: this.editForm.get(['nameAr'])!.value,
      code: this.editForm.get(['code'])!.value,
      order: this.editForm.get(['order'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInspectionRequirement>>): void {
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
