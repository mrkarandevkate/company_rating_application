import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyratingComponent } from './companyrating.component';

describe('CompanyratingComponent', () => {
  let component: CompanyratingComponent;
  let fixture: ComponentFixture<CompanyratingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompanyratingComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompanyratingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
