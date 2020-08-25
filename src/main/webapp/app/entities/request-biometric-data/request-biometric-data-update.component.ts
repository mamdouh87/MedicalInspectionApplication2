import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IRequestBiometricData, RequestBiometricData } from 'app/shared/model/request-biometric-data.model';
import { RequestBiometricDataService } from './request-biometric-data.service';
import { AlertError } from 'app/shared/alert/alert-error.model';

@Component({
  selector: 'jhi-request-biometric-data-update',
  templateUrl: './request-biometric-data-update.component.html',
})
export class RequestBiometricDataUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    image: [],
    imageContentType: [],
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected requestBiometricDataService: RequestBiometricDataService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestBiometricData }) => {
      this.updateForm(requestBiometricData);
    });
  }

  updateForm(requestBiometricData: IRequestBiometricData): void {
    this.editForm.patchValue({
      id: requestBiometricData.id,
      image: requestBiometricData.image,
      imageContentType: requestBiometricData.imageContentType,
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('medicalInspectionApplicationApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requestBiometricData = this.createFromForm();
    if (requestBiometricData.id !== undefined) {
      this.subscribeToSaveResponse(this.requestBiometricDataService.update(requestBiometricData));
    } else {
      this.subscribeToSaveResponse(this.requestBiometricDataService.create(requestBiometricData));
    }
  }

  private createFromForm(): IRequestBiometricData {
    return {
      ...new RequestBiometricData(),
      id: this.editForm.get(['id'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequestBiometricData>>): void {
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
