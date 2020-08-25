import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IRequestBiometricData } from 'app/shared/model/request-biometric-data.model';

@Component({
  selector: 'jhi-request-biometric-data-detail',
  templateUrl: './request-biometric-data-detail.component.html',
})
export class RequestBiometricDataDetailComponent implements OnInit {
  requestBiometricData: IRequestBiometricData | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestBiometricData }) => (this.requestBiometricData = requestBiometricData));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType = '', base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
