import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';

@Component({
  selector: 'jhi-medical-insepction-requests-detail',
  templateUrl: './medical-insepction-requests-detail.component.html',
})
export class MedicalInsepctionRequestsDetailComponent implements OnInit {
  medicalInsepctionRequests: IMedicalInsepctionRequests | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ medicalInsepctionRequests }) => (this.medicalInsepctionRequests = medicalInsepctionRequests));
  }

  previousState(): void {
    window.history.back();
  }
}
