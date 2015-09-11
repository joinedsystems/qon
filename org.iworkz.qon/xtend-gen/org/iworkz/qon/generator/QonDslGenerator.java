/**
 * generated by Xtext
 */
package org.iworkz.qon.generator;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.Extension;
import org.iworkz.qon.helper.PropertyHelper;
import org.iworkz.qon.qonDsl.QObject;

/**
 * Generates code from your model files on save.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
@SuppressWarnings("all")
public class QonDslGenerator implements IGenerator {
  @Inject
  @Extension
  private PropertyHelper _propertyHelper;
  
  @Override
  public void doGenerate(final Resource it, final IFileSystemAccess fsa) {
    final QObject rootObject = this.rootQObjectOfResource(it);
    QObject _type = null;
    if (rootObject!=null) {
      _type=rootObject.getType();
    }
    String _stringProperty = null;
    if (_type!=null) {
      _stringProperty=this._propertyHelper.getStringProperty(_type, "name");
    }
    boolean _equals = Objects.equal("Schema", _stringProperty);
    if (_equals) {
      URI _uRI = it.getURI();
      URI _trimFileExtension = _uRI.trimFileExtension();
      final String name = _trimFileExtension.lastSegment();
      CharSequence _fileHeader = this.fileHeader(name);
      fsa.generateFile((name + ".xtext"), _fileHeader);
    }
  }
  
  public QObject rootQObjectOfResource(final Resource resource) {
    boolean _and = false;
    EList<EObject> _contents = resource.getContents();
    int _size = _contents.size();
    boolean _greaterThan = (_size > 0);
    if (!_greaterThan) {
      _and = false;
    } else {
      EList<EObject> _contents_1 = resource.getContents();
      EObject _get = _contents_1.get(0);
      _and = (_get instanceof QObject);
    }
    if (_and) {
      EList<EObject> _contents_2 = resource.getContents();
      EObject _get_1 = _contents_2.get(0);
      return ((QObject) _get_1);
    }
    return null;
  }
  
  public CharSequence fileHeader(final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("grammar org.iworkz.demo.");
    _builder.append(name, "");
    _builder.append("Dsl with org.eclipse.xtext.common.Terminals");
    _builder.newLineIfNotEmpty();
    _builder.newLine();
    _builder.append("/* generate schemaDsl \"http://www.iworkz.org/demo/");
    _builder.append(name, "");
    _builder.append("Dsl\" */");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
