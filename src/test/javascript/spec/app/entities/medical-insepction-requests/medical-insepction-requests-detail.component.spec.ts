import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { MedicalInsepctionRequestsDetailComponent } from 'app/entities/medical-insepction-requests/medical-insepction-requests-detail.component';
import { MedicalInsepctionRequests } from 'app/shared/model/medical-insepction-requests.model';

describe('Component Tests', () => {
  describe('MedicalInsepctionRequests Management Detail Component', () => {
    let comp: MedicalInsepctionRequestsDetailComponent;
    let fixture: ComponentFixture<MedicalInsepctionRequestsDetailComponent>;
    const route = ({ data: of({ medicalInsepctionRequests: new MedicalInsepctionRequests(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [MedicalInsepctionRequestsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MedicalInsepctionRequestsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MedicalInsepctionRequestsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load medicalInsepctionRequests on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.medicalInsepctionRequests).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
