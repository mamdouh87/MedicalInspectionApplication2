import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInspectionResult } from 'app/shared/model/inspection-result.model';

@Component({
  selector: 'jhi-inspection-result-detail',
  templateUrl: './inspection-result-detail.component.html',
})
export class InspectionResultDetailComponent implements OnInit {
  inspectionResult: IInspectionResult | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inspectionResult }) => (this.inspectionResult = inspectionResult));
  }

  previousState(): void {
    window.history.back();
  }
}
