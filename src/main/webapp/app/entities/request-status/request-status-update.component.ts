import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRequestStatus, RequestStatus } from 'app/shared/model/request-status.model';
import { RequestStatusService } from './request-status.service';

@Component({
  selector: 'jhi-request-status-update',
  templateUrl: './request-status-update.component.html',
})
export class RequestStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameEn: [],
    nameAr: [],
    code: [],
  });

  constructor(protected requestStatusService: RequestStatusService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestStatus }) => {
      this.updateForm(requestStatus);
    });
  }

  updateForm(requestStatus: IRequestStatus): void {
    this.editForm.patchValue({
      id: requestStatus.id,
      nameEn: requestStatus.nameEn,
      nameAr: requestStatus.nameAr,
      code: requestStatus.code,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requestStatus = this.createFromForm();
    if (requestStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.requestStatusService.update(requestStatus));
    } else {
      this.subscribeToSaveResponse(this.requestStatusService.create(requestStatus));
    }
  }

  private createFromForm(): IRequestStatus {
    return {
      ...new RequestStatus(),
      id: this.editForm.get(['id'])!.value,
      nameEn: this.editForm.get(['nameEn'])!.value,
      nameAr: this.editForm.get(['nameAr'])!.value,
      code: this.editForm.get(['code'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequestStatus>>): void {
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
