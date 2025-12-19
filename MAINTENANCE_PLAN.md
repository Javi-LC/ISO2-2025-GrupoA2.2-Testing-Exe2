# Maintenance Management Plan - Exe2 Project
**Date**: December 19, 2025  
**Version**: 0.0.1-SNAPSHOT  
**Lab Session**: 5 - Maintenance Management (ISO II)

---

## Executive Summary

This maintenance plan documents the analysis of code quality issues detected by Maven quality checker plugins and defines corrective and perfective maintenance actions for the Exe2 airline fare calculator project.

### Quality Analysis Summary

| Tool | Focus Area | Issues Found | Priority |
|------|-----------|--------------|----------|
| **PMD** | Code Quality | ~45 violations | Medium-High |
| **Checkstyle** | Style Standards | 165 errors | High |
| **CPD** | Code Duplication | 0 duplications | ✓ Good |

---

## 1. Quality Report Analysis

### 1.1 PMD Report - Code Quality Issues

**Total Violations**: ~45 (Priority 3)

#### Critical Issues (High Priority):
1. **AssignmentInOperand** (5 violations in FareService.java)
   - Lines: 35, 36, 37, 38, 39
   - Risk: Reduces code readability and maintainability
   - Example: `if ((offer = checkPajarilloFare(...)) != null) return offer;`

2. **IfStmtsMustUseBraces** (5 violations in FareService.java)
   - Lines: 35, 36, 37, 38, 39
   - Risk: Error-prone for future modifications
   - Single-line if statements without braces

3. **OnlyOneReturn** (Multiple violations in FareService.java)
   - Multiple exit points in methods
   - Risk: Harder to debug and trace execution flow

#### Moderate Priority Issues:
1. **PackageCase** (4 violations - all files)
   - Package name `ISO2.Exe2` uses uppercase letters
   - Should be: `iso2.exe2`
   - Impact: Violates Java naming conventions

2. **MethodArgumentCouldBeFinal** (45+ violations)
   - Parameters not declared as final when not reassigned
   - Impact: Reduces immutability guarantees

3. **LongVariable** (3 violations)
   - `discountPercentage` (FareOffer.java)
   - `travelsWithChildren` (FareService.java)
   - Impact: Minor readability issue

4. **AtLeastOneConstructor** (1 violation - FareService.java)
   - Class doesn't explicitly declare a constructor
   - Impact: Less explicit about initialization

### 1.2 Checkstyle Report - Style Violations

**Total Errors**: 165

#### Critical Categories:
1. **FinalParameters** (45 errors) - Most frequent
   - Parameters should be declared final
   - Matches PMD findings

2. **LineLength** (25 errors)
   - Lines exceed 80 characters
   - Readability issue on smaller screens

3. **JavadocMethod** (24 errors)
   - Missing Javadoc for methods
   - Impact: Poor documentation

4. **RegexpSingleline** (21 errors - trailing spaces)
   - Whitespace inconsistencies
   - Impact: Git diff pollution

5. **MagicNumber** (18 errors)
   - Hard-coded numeric literals
   - Should use named constants

6. **JavadocVariable** (8 errors)
   - Missing Javadoc for enum constants and fields

7. **InnerAssignment** (5 errors)
   - Assignment within conditions
   - Same as PMD's AssignmentInOperand

8. **NeedBraces** (5 errors)
   - If statements without braces
   - Same as PMD's IfStmtsMustUseBraces

9. **PackageName** (4 errors)
   - Package naming convention violation
   - Same as PMD finding

10. **JavadocPackage** (2 errors)
    - Missing package-info.java files

### 1.3 CPD Report - Code Duplication

**Result**: ✓ **No code duplication detected**

Excellent result - indicates no copy-paste patterns in the codebase.

---

## 2. Root Cause Analysis

### 2.1 Structural Issues
- **Package Naming**: Project created with uppercase package names (ISO2.Exe2) violating Java conventions
- **Missing Package Documentation**: No package-info.java files

### 2.2 Code Style Issues
- **Inconsistent Formatting**: Trailing spaces, line length violations
- **Documentation Gaps**: Missing Javadoc for methods and variables
- **Magic Numbers**: Hard-coded values instead of named constants

### 2.3 Code Quality Issues
- **Assignment in Conditions**: Complex conditional expressions
- **Multiple Return Points**: Methods with early returns
- **Missing Braces**: Single-line statements without braces
- **Mutability**: Parameters not declared final

---

## 3. Maintenance Actions

### 3.1 Corrective Maintenance (Bug Fixes & Standards Compliance)

#### Priority 1 - Critical (Immediate Action Required)

**CM-1: Refactor Assignment in Operands**
- **Location**: FareService.java, lines 35-39
- **Issue**: Assignment within if conditions
- **Action**: Extract assignments to separate statements
- **Estimated Effort**: 1 hour
- **Risk if not fixed**: Low code quality, hard to debug

**CM-2: Add Braces to If Statements**
- **Location**: FareService.java, lines 35-39
- **Issue**: If statements without braces
- **Action**: Add curly braces to all single-line if statements
- **Estimated Effort**: 30 minutes
- **Risk if not fixed**: Future bugs when adding statements

**CM-3: Replace Magic Numbers with Constants**
- **Location**: FareService.java (multiple locations)
- **Issue**: 18 hard-coded numeric literals
- **Action**: Create private static final constants
- **Estimated Effort**: 2 hours
- **Risk if not fixed**: Difficult to maintain and understand business rules

#### Priority 2 - Important (Short-term Action)

**CM-4: Fix Line Length Violations**
- **Location**: All files (25 occurrences)
- **Issue**: Lines exceed 80 characters
- **Action**: Refactor long lines
- **Estimated Effort**: 1 hour
- **Risk if not fixed**: Reduced readability

**CM-5: Remove Trailing Spaces**
- **Location**: All files (21 occurrences)
- **Issue**: Whitespace at end of lines
- **Action**: Configure IDE to auto-trim on save
- **Estimated Effort**: 15 minutes
- **Risk if not fixed**: Git diff noise

**CM-6: Add Method Documentation**
- **Location**: All service methods (24 missing)
- **Issue**: Missing Javadoc comments
- **Action**: Add complete Javadoc with @param and @return
- **Estimated Effort**: 3 hours
- **Risk if not fixed**: Poor maintainability

#### Priority 3 - Nice to Have (Long-term Action)

**CM-7: Declare Parameters as Final**
- **Location**: All methods (45 violations)
- **Issue**: Mutable parameters
- **Action**: Add final modifier to all method parameters
- **Estimated Effort**: 1 hour
- **Risk if not fixed**: Minor - reduces immutability guarantees

**CM-8: Add Javadoc for Variables**
- **Location**: Model classes (8 enum constants/fields)
- **Issue**: Missing Javadoc comments
- **Action**: Document all public fields and enum constants
- **Estimated Effort**: 30 minutes
- **Risk if not fixed**: Documentation completeness

### 3.2 Perfective Maintenance (Improvements)

#### PM-1: Refactor Package Structure
- **Current**: `ISO2.Exe2`
- **Proposed**: `iso2.exe2` or `es.udc.iso2.exe2`
- **Benefit**: Follows Java naming conventions
- **Estimated Effort**: 2 hours (refactoring + testing)
- **Impact**: High - requires significant refactoring

**Decision**: **DEFERRED** - Major refactoring requiring coordination with other project components. Document as technical debt.

#### PM-2: Refactor Multiple Return Statements
- **Location**: FareService.java methods
- **Current**: Early returns in methods
- **Proposed**: Single exit point with result variable
- **Benefit**: Easier debugging and logging
- **Estimated Effort**: 2 hours
- **Impact**: Medium - controversial practice (some prefer early returns)

**Decision**: **OPTIONAL** - Early returns can improve readability. Will implement selectively based on complexity.

#### PM-3: Add Explicit Constructor to FareService
- **Location**: FareService.java
- **Issue**: No explicit constructor
- **Action**: Add public no-args constructor
- **Benefit**: Makes initialization explicit
- **Estimated Effort**: 15 minutes

#### PM-4: Refactor Long Variable Names
- **Location**: `discountPercentage`, `travelsWithChildren`
- **Proposed**: `discount`, `withChildren`
- **Benefit**: Improved readability (debatable)
- **Estimated Effort**: 30 minutes

**Decision**: **DEFERRED** - Variable names are already clear and descriptive.

#### PM-5: Add package-info.java Files
- **Location**: ISO2.Exe2.model, ISO2.Exe2.service packages
- **Action**: Create package documentation files
- **Benefit**: Package-level Javadoc
- **Estimated Effort**: 30 minutes

---

## 4. Implementation Plan

### Phase 1: Quick Wins (Week 1)
**Goal**: Address critical code quality issues

- [x] Setup: Create maintenance branch
- [ ] CM-1: Refactor assignment in operands
- [ ] CM-2: Add braces to if statements
- [ ] CM-5: Remove trailing spaces
- [ ] PM-3: Add explicit constructor

**Expected Reduction**: ~15 violations

### Phase 2: Documentation (Week 2)
**Goal**: Improve code documentation

- [ ] CM-6: Add method Javadoc (24 methods)
- [ ] CM-8: Add variable Javadoc (8 fields)
- [ ] PM-5: Add package-info.java files

**Expected Reduction**: ~35 violations

### Phase 3: Standards Compliance (Week 3)
**Goal**: Address style violations

- [ ] CM-3: Replace magic numbers with constants
- [ ] CM-4: Fix line length violations
- [ ] CM-7: Add final to parameters (if agreed)

**Expected Reduction**: ~90 violations

### Phase 4: Review & Validation (Week 4)
**Goal**: Verify improvements

- [ ] Re-generate all quality reports
- [ ] Compare before/after metrics
- [ ] Update documentation
- [ ] Code review session
- [ ] Merge to main branch

---

## 5. Success Criteria

### Quantitative Metrics

| Metric | Current | Target | Improvement |
|--------|---------|--------|-------------|
| PMD Violations | 45 | ≤ 10 | 78% reduction |
| Checkstyle Errors | 165 | ≤ 20 | 88% reduction |
| CPD Duplications | 0 | 0 | Maintained |
| Javadoc Coverage | ~30% | ≥ 90% | +60% |
| Magic Numbers | 18 | 0 | 100% reduction |

### Qualitative Criteria

- ✓ All public APIs documented
- ✓ No assignments in conditionals
- ✓ All control structures use braces
- ✓ No trailing whitespace
- ✓ Lines ≤ 100 characters
- ✓ Code review approved

---

## 6. Risk Assessment

### High Risk - Deferred Items

| Item | Risk | Mitigation |
|------|------|------------|
| Package Renaming (PM-1) | Breaking changes, dependencies | Document as technical debt, plan for major version |
| Multiple Returns (PM-2) | Subjective improvement, team preference | Review with team before implementing |

### Medium Risk - Planned Items

| Item | Risk | Mitigation |
|------|------|------------|
| Magic Number Constants (CM-3) | May change business logic interpretation | Thorough testing, name constants clearly |
| Parameter Final (CM-7) | Many files affected, merge conflicts | Coordinate with team, separate commit |

### Low Risk - Safe Changes

| Item | Risk | Mitigation |
|------|------|------------|
| Braces (CM-2) | None | Automated formatting |
| Trailing Spaces (CM-5) | None | IDE configuration |
| Javadoc (CM-6, CM-8) | None | Additive only |

---

## 7. Tools & Configuration

### Configured Maven Plugins

1. **maven-pmd-plugin** (3.21.0)
   - Rulesets: basic, imports, unnecessary, braces, naming, design, coupling, controversial, optimizations
   - Status: ✓ Working

2. **spotbugs-maven-plugin** (4.8.2.0)
   - Status: ⚠ Disabled (Java 21 compatibility issue)
   - Note: Re-enable after Java 8 compilation or SpotBugs update

3. **maven-checkstyle-plugin** (3.3.1)
   - Configuration: sun_checks.xml
   - Status: ✓ Working

4. **maven-jxr-plugin** (3.3.1)
   - Status: ✓ Working (source cross-reference)

5. **maven-javadoc-plugin** (3.6.3)
   - Status: ⚠ Partial (some errors)
   - Note: Will improve with CM-6 and CM-8

### Recommended IDE Settings

```xml
<!-- Eclipse/IntelliJ Configuration -->
- Remove trailing whitespace on save: ENABLED
- Organize imports on save: ENABLED
- Format on save: ENABLED
- Line length: 100 characters
- Indent: 4 spaces (no tabs)
```

---

## 8. Deferred Items (Technical Debt Register)

### TD-1: Package Naming Convention
**Issue**: Package names use uppercase (ISO2.Exe2)  
**Impact**: Violates Java conventions (4 files affected)  
**Effort**: High (2-3 hours refactoring)  
**Decision**: Defer to version 1.0.0  
**Reason**: Requires coordination with other modules and extensive testing

### TD-2: SpotBugs Integration
**Issue**: SpotBugs disabled due to Java 21 incompatibility  
**Impact**: Missing bug pattern detection  
**Effort**: Low (re-enable after fix)  
**Decision**: Defer until Java 8 target confirmed or SpotBugs update  
**Reason**: Technical limitation

### TD-3: CommandLine Dependency
**Issue**: ISO2:CommandLine:0.0.3 not properly installed in Maven repository  
**Impact**: App.java cannot compile  
**Effort**: Low (proper installation)  
**Decision**: Defer pending instructor guidance  
**Reason**: External dependency issue

---

## 9. Validation & Testing Strategy

### Pre-Implementation
1. ✓ Create feature/maintenance-lab5 branch
2. ✓ Generate baseline quality reports
3. ✓ Document current metrics

### During Implementation
1. Run `mvn clean compile` after each change
2. Run `mvn site:site` periodically to check progress
3. Review diff carefully before committing

### Post-Implementation
1. Generate final quality reports
2. Compare before/after metrics
3. Run all unit tests: `mvn test`
4. Verify no regressions
5. Update this document with results

---

## 10. Next Steps

### Immediate Actions (Today)
1. ✓ Complete this maintenance plan
2. [ ] Create Git branch: `feature/maintenance-lab5`
3. [ ] Implement Phase 1 quick wins (CM-1, CM-2, CM-5)
4. [ ] Commit and verify improvements

### Short-term (This Week)
1. [ ] Complete Phase 2 (documentation)
2. [ ] Get code review feedback
3. [ ] Adjust plan based on feedback

### Documentation
1. [ ] Take before/after screenshots of quality reports
2. [ ] Document lessons learned
3. [ ] Present findings to team

---

## 11. References

- PMD Rule Documentation: https://pmd.github.io/pmd-6.55.0/
- Checkstyle Documentation: https://checkstyle.org/config.html
- Java Code Conventions: https://www.oracle.com/java/technologies/javase/codeconventions-contents.html
- Maven Site Plugin: https://maven.apache.org/plugins/maven-site-plugin/

---

## Appendix A: Quality Report Locations

- **Main Report**: `target/site/index.html`
- **PMD Report**: `target/site/pmd.html`
- **Checkstyle Report**: `target/site/checkstyle.html`
- **CPD Report**: `target/site/cpd.html`
- **Source Xref**: `target/site/xref/`
- **Test Coverage**: `target/site/jacoco/`

---

## Appendix B: Command Reference

```bash
# Generate all reports
mvn clean site:site -DskipTests

# Run tests
mvn test

# View specific plugin reports
mvn pmd:pmd
mvn checkstyle:checkstyle

# Clean and recompile
mvn clean compile

# Package the project
mvn clean package
```

---

**Document Status**: ✓ Complete  
**Last Updated**: December 19, 2025  
**Next Review**: After Phase 1 completion
