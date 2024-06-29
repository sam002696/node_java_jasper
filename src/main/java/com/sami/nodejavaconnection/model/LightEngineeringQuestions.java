package com.sami.nodejavaconnection.model;


import lombok.Data;

import java.util.List;

@Data
public class LightEngineeringQuestions {

    private String title;
    private String conductedBy;
    private GeneralInfo generalInfo;
    private Organization organization;
    private RawMaterial rawMaterial;
    private Manufacturing manufacturing;
    private MachineUsed machineUsed;
    private SafetyMeasures safetyMeasures;
    private SafetyRelations safetyRelations;
    private HouseKeeping houseKeeping;
    private ExistingMachinerySafety existingMachinerySafety;
    private ProductOutput productOutput;
    private Identification identification;

    @Data
    public static class GeneralInfo {
        private String industryName;
        private String location;
        private String address;
        private int yearEstablished;
        private String tel;
        private String fax;
        private String proprietorName;
        private String educationalBackground;
        private String trainingAchieved;
        private String productType;
        private String productDemand;
        private String unitPrice;
        private List<ProductDecision> productDecision;

        @Data
        public static class ProductDecision {
            private String decision;
        }
    }

    @Data
    public static class Organization {
        private String fixedCapital;
        private String machinery;
        private String land;
        private String building;
        private String workerWages;
        private String others;
        private String financeSource;
        private int fullTimeEmployees;
        private int partTimeEmployees;
    }

    @Data
    public static class RawMaterial {
        private String rawMaterialRequired;
        private List<MaterialSupplier> materialSupplier;
        private String qualityTest;
        private String intendedTest;
        private List<JudgeRawMaterial> judgeRawMaterial;

        @Data
        public static class MaterialSupplier {
            private String supplierType;
        }

        @Data
        public static class JudgeRawMaterial {
            private String judge;
        }
    }

    @Data
    public static class Manufacturing {
        private List<ManufacturingProcessRequired> manufacturingProcessRequired;
        private List<MaintenanceAvailability> maintenanceAvailability;
        private int noOfUnSkilledEmployees;
        private int noOfSkilledEmployees;
        private int noOfSemiSkilledEmployees;
        private List<SkilledLevel> skilledLevel;
        private List<SemiSkilledLevel> semiSkilledLevel;
        private List<UnskilledLevel> unskilledLevel;
        private String safetyKnowledgeLevel;
        private String operationSpace;
        private String storeSpace;

        @Data
        public static class ManufacturingProcessRequired {
            private String processType;
        }

        @Data
        public static class MaintenanceAvailability {
            private String type;
        }

        @Data
        public static class SkilledLevel {
            private String levelYear;
        }

        @Data
        public static class SemiSkilledLevel {
            private String levelYear;
        }

        @Data
        public static class UnskilledLevel {
            private String levelYear;
        }
    }

    @Data
    public static class MachineUsed {
        private List<MachineUsedInfo> machineUsedInfo;

        @Data
        public static class MachineUsedInfo {
            private String machineType;
            private int machineQuantity;
            private String machineCountry;
            private String machineAccuracy;
            private String machineUsageTime;
            private String machineAgeType;
            private String machineYearUsage;
            private String machineOthers;
        }
    }

    @Data
    public static class SafetyMeasures {
        private String stateOfElectricWiring;
        private List<FireFightingGears> fireFightingGears;
        private String firstAidBox;
        private String nearestMedicalAid;
        private List<SafetyMeasuresAvailable> safetyMeasuresAvailable;

        @Data
        public static class FireFightingGears {
            private String gearType;
        }

        @Data
        public static class SafetyMeasuresAvailable {
            private String machineType;
            private String machineGuardingSystem;
            private String machineEmergencyStop;
            private String machineColorUsedSafetySigns;
            private String machineLubricationSystem;
            private String machineInterLockingSystem;
            private String machineElectricalProtection;
            private String machineOthers;
        }
    }

    @Data
    public static class SafetyRelations {
        private List<SafetyRelatedInfo> safetyRelatedInfo;
        private String exerciseSafetyMeasuresAvailability;
        private String safetyMeasureAcknowledgement;
        private String safeOperationsTraining;
        private String employeeSafeguardKnowledge;
        private String safeGuardsRemovalAcknowledgement;
        private String safeGuardUnavailabilityAcknowledgement;

        @Data
        public static class SafetyRelatedInfo {
            private String machineType;
            private int machineAccidentRate;
            private String machineInjuryType;
            private String machineAbsenteeism;
            private int machineExpenses;
            private String machineTurnOverRate;
        }
    }

    @Data
    public static class HouseKeeping {
        private List<HouseKeepingInfo> houseKeepingInfo;

        @Data
        public static class HouseKeepingInfo {
            private String practiceType;
            private String cleaningOftenDone;
            private String cleaningEmployee;
            private String toolsEquipment;
            private String frequentlyNeededTool;
            private String panelsNeededTool;
            private String gears;
            private String spread;
            private String spill;
        }
    }

    @Data
    public static class ExistingMachinerySafety {
        private List<ExistingMachinerySafetyInfo> existingMachinerySafetyInfo;

        @Data
        public static class ExistingMachinerySafetyInfo {
            private String machineType;
            private String machineSafeguard;
            private String machineSafetyEquipment;
            private String machineTrainingEmployee;
            private String machineMaintenanceSchedule;
            private String machineReplacingMc;
            private String machineAvailableToolbox;
        }
    }

    @Data
    public static class ProductOutput {
        private String prHourly;
        private String prDaily;
        private String prWeekly;
        private String prMonthly;
        private String prOrderBased;
        private int prDefectRate;
        private int prReworkRate;
        private List<DefectSource> defectSource;
        private String qdDaily;
        private String qdWeekly;
        private String qdMonthly;
        private String qdOrderBased;

        @Data
        public static class DefectSource {
            private String source;
        }
    }

    @Data
    public static class Identification {
        private List<Financial> financial;
        private List<Technical> technical;
        private List<Managerial> managerial;
        private List<NeedPriority> needPriority;

        @Data
        public static class Financial {
            private String financialType;
        }

        @Data
        public static class Technical {
            private String technicalType;
        }

        @Data
        public static class Managerial {
            private String managerialType;
        }

        @Data
        public static class NeedPriority {
            private String priorityType;
        }
    }
}

