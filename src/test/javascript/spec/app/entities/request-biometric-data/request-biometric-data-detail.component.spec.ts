import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { MedicalInspectionApplicationTestModule } from '../../../test.module';
import { RequestBiometricDataDetailComponent } from 'app/entities/request-biometric-data/request-biometric-data-detail.component';
import { RequestBiometricData } from 'app/shared/model/request-biometric-data.model';

describe('Component Tests', () => {
  describe('RequestBiometricData Management Detail Component', () => {
    let comp: RequestBiometricDataDetailComponent;
    let fixture: ComponentFixture<RequestBiometricDataDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ requestBiometricData: new RequestBiometricData(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MedicalInspectionApplicationTestModule],
        declarations: [RequestBiometricDataDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RequestBiometricDataDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RequestBiometricDataDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load requestBiometricData on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.requestBiometricData).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
