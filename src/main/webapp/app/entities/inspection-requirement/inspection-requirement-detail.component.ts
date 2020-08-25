import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInspectionRequirement } from 'app/shared/model/inspection-requirement.model';

@Component({
  selector: 'jhi-inspection-requirement-detail',
  templateUrl: './inspection-requirement-detail.component.html',
})
export class InspectionRequirementDetailComponent implements OnInit {
  inspectionRequirement: IInspectionRequirement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ inspectionRequirement }) => (this.inspectionRequirement = inspectionRequirement));
  }

  previousState(): void {
    window.history.back();
  }
}
